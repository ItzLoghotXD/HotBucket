package me.itzloghotxd.hotbucket;

import com.google.common.collect.ImmutableMap;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.logging.Logger;
import me.itzloghotxd.hotbucket.command.SimpleCommandMap;
import me.itzloghotxd.hotbucket.plugin.PluginManager;
import me.itzloghotxd.hotbucket.plugin.SimplePluginManager;

public final class TestServer implements InvocationHandler {
    private static interface MethodHandler {
        Object handle(TestServer server, Object[] args);
    }

    private static final Map<Method, MethodHandler> methods;

    static {
        try {
            ImmutableMap.Builder<Method, MethodHandler> methodMap = ImmutableMap.builder();
            methodMap.put(
                    Server.class.getMethod("isPrimaryThread"),
                    new MethodHandler() {
                        @Override
                        public Object handle(TestServer server, Object[] args) {
                            return Thread.currentThread().equals(server.creatingThread);
                        }
                    }
                );
            methodMap.put(
                    Server.class.getMethod("getPluginManager"),
                    new MethodHandler() {
                        @Override
                        public Object handle(TestServer server, Object[] args) {
                            return server.pluginManager;
                        }
                    }
                );
            methodMap.put(
                    Server.class.getMethod("getLogger"),
                    new MethodHandler() {
                        final Logger logger = Logger.getLogger(TestServer.class.getCanonicalName());
                        @Override
                        public Object handle(TestServer server, Object[] args) {
                            return logger;
                        }
                    }
                );
            methodMap.put(
                    Server.class.getMethod("getName"),
                    new MethodHandler() {
                        @Override
                        public Object handle(TestServer server, Object[] args) {
                            return TestServer.class.getSimpleName();
                        }
                    }
                );
            methodMap.put(
                    Server.class.getMethod("getVersion"),
                    new MethodHandler() {
                        @Override
                        public Object handle(TestServer server, Object[] args) {
                            return "Version_" + TestServer.class.getPackage().getImplementationVersion();
                        }
                    }
                );
            methodMap.put(
                    Server.class.getMethod("getBukkitVersion"),
                    new MethodHandler() {
                        @Override
                        public Object handle(TestServer server, Object[] args) {
                            return "BukkitVersion_" + TestServer.class.getPackage().getImplementationVersion();
                        }
                    }
                );
            methods = methodMap.build();

            TestServer server = new TestServer();
            Server instance = Proxy.getProxyClass(Server.class.getClassLoader(), Server.class).asSubclass(Server.class).getConstructor(InvocationHandler.class).newInstance(server);
            Bukkit.setServer(instance);
            server.pluginManager = new SimplePluginManager(instance, new SimpleCommandMap(instance));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

    private Thread creatingThread = Thread.currentThread();
    private PluginManager pluginManager;
    private TestServer() {};

    public static Server getInstance() {
        return Bukkit.getServer();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        MethodHandler handler = methods.get(method);
        if (handler != null) {
            return handler.handle(this, args);
        }
        throw new UnsupportedOperationException(String.valueOf(method));
    }
}

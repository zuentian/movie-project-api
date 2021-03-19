package JAVA高并发编程详解.chapter21;

public class ActionContext {

    //定义ThreadLocal，并且使用Supplier的方式重写initValue
    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);

    public static Context get(){
        return context.get();
    }

    //每一个线程都会有一个独立的Context实例
    static class Context{
        private Configuration configuration;
        private OtherResource otherResource;

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }

        public OtherResource getOtherResource() {
            return otherResource;
        }

        public void setOtherResource(OtherResource otherResource) {
            this.otherResource = otherResource;
        }
    }
}

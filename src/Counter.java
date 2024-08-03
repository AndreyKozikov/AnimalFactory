class Counter implements AutoCloseable {
    private static Counter instance;
    private int count;
    private boolean open;

    private Counter() {
        this.count = 0;
        this.open = false;
    }

    // Метод для получения единственного экземпляра Counter
    public static Counter getInstance() {
        if (instance == null) {
            instance = new Counter();
        }
        return instance;
    }

    public void add() {
        if (!open) {
            throw new IllegalStateException("Counter is not open!");
        }
        count++;
    }

    @Override
    public void close() {
        if (!open) {
            throw new IllegalStateException("Counter was not open!");
        }
        open = false;
        System.out.println("Counter closed. Total count: " + count);
    }

    public void open() {
        if (open) {
            throw new IllegalStateException("Counter is already open!");
        }
        open = true;
    }
}

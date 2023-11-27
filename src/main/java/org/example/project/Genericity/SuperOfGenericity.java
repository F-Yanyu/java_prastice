package org.example.project.Genericity;

public class SuperOfGenericity {
    public static void main(String[] args) {
        /**
         * @super通配符
         *
         * 和extends通配符相反，这次，我们希望接受Pair<Integer>类型，以及Pair<Number>、Pair<Object>，
         * 因为Number和Object是Integer的父类，setFirst(Number)和setFirst(Object)实际上允许接受Integer类型。
         *
         * 我们使用super通配符来改写这个方法：
         * void set(Pair<? super Integer> p, Integer first, Integer last) {
         *     p.setFirst(first);
         *     p.setLast(last);
         * }
         * 注意到Pair<? super Integer>表示，方法参数接受所有泛型类型为Integer或Integer父类的Pair类型。
         *
         * 这里注意到我们无法使用Integer类型来接收getFirst()的返回值，即下面的语句将无法通过编译：
         * Integer x = p.getFirst();
         * 因为如果传入的实际类型是Pair<Number>，编译器无法将Number类型转型为Integer。
         * 注意：虽然Number是一个抽象类，我们无法直接实例化它。但是，即便Number不是抽象类，这里仍然无法通过编译。此外，传入Pair<Object>类型时，编译器也无法将Object类型转型为Integer。
         * 唯一可以接收getFirst()方法返回值的是Object类型：
         * Object obj = p.getFirst();
         *
         * 因此，使用<? super Integer>通配符表示：
         *      - 允许调用set(? super Integer)方法传入Integer的引用；
         *      - 不允许调用get()方法获得Integer的引用。
         *  - 唯一例外是可以获取Object的引用：Object o = p.getFirst()。
         *  - 换句话说，使用<? super Integer>通配符作为方法参数，表示方法内部代码对于参数只能写，不能读。
         *
         * @对比extends和super通配符
         * <? extends T>类型和<? super T>类型的区别在于
         *      - <? extends T>允许调用读方法T get()获取T的引用，但不允许调用写方法set(T)传入T的引用（传入null除外）；
         *      - <? super T>允许调用写方法set(T)传入T的引用，但不允许调用读方法T get()获取T的引用（获取Object除外）。
         * 一个是允许读不允许写，另一个是允许写不允许读。
         *
         *
         * @PECS原则
         * 何时使用extends，何时使用super？为了便于记忆，我们可以用PECS原则：Producer（生产者） Extends Consumer（消费者） Super。
         *
         *
         * @无限定通配符
         * 我们已经讨论了<? extends T>和<? super T>作为方法参数的作用。实际上，Java的泛型还允许使用无限定通配符（Unbounded Wildcard Type），即只定义一个?：
         * void sample(Pair<?> p) {
         * }
         * 因为<?>通配符既没有extends，也没有super，因此：
         *
         *      - 不允许调用set(T)方法并传入引用（null除外）；
         *      - 不允许调用T get()方法并获取T引用（只能获取Object引用）。
         * 换句话说，既不能读，也不能写，那只能做一些null判断：
         * static boolean isNull(Pair<?> p) {
         *     return p.getFirst() == null || p.getLast() == null;
         * }
         * 大多数情况下，可以引入泛型参数<T>消除<?>通配符：
         * static <T> boolean isNull(Pair<T> p) {
         *     return p.getFirst() == null || p.getLast() == null;
         * }
         *
         * <?>通配符有一个独特的特点，就是：Pair<?>是所有Pair<T>的超类：
         *  Pair<Integer> p = new Pair<>(123, 456);
         *  Pair<?> p2 = p; // 安全地向上转型
         *  因为Pair<Integer>是Pair<?>的子类，可以安全地向上转型。
         *
         *
         * @小结
         * 使用类似<? super Integer>通配符作为方法参数时表示：
         *
         *      - 方法内部可以调用传入Integer引用的方法，例如：obj.setFirst(Integer n);；
         *
         *      - 方法内部无法调用获取Integer引用的方法（Object除外），例如：Integer n = obj.getFirst();。
         *
         * 即使用super通配符表示只能写不能读。
         *
         * 使用extends和super通配符要遵循PECS原则。
         *
         * 无限定通配符<?>很少使用，可以用<T>替换，同时它是所有<T>类型的超类。
         */
        // 下面的代码可以被正常编译：
        Pair<Number> p1 = new Pair<>(12.3, 4.56);
        Pair<Integer> p2 = new Pair<>(123, 456);
        setSame(p1, 100);
        setSame(p2, 200);
        System.out.println(p1.getFirst() + ", " + p1.getLast());
        System.out.println(p2.getFirst() + ", " + p2.getLast());

    }

    static void setSame(Pair<? super Integer> p, Integer n) {
        p.setFirst(n);
        p.setLast(n);
    }
}

class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }
    public void setFirst(T first) {
        this.first = first;
    }
    public void setLast(T last) {
        this.last = last;
    }
}

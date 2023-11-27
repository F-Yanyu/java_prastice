package org.example.project.Genericity;

public class UsingGenericity {
    public static void main(String[] args) {
        /**
         * @使用泛型
         * 使用ArrayList时，如果不定义泛型类型时，泛型类型实际上就是Object
         * 当我们定义泛型类型<String>后，List<T>的泛型接口变为强类型List<String>：
         *      // 无编译器警告:
         *      List<String> list = new ArrayList<String>();
         *      list.add("Hello");
         *      list.add("World");
         *      // 无强制转型:
         *      String first = list.get(0);
         *      String second = list.get(1);
         *
         * 编译器如果能自动推断出泛型类型，就可以省略后面的泛型类型。
         * 编译器看到泛型类型List<Number>就可以自动推断出后面的ArrayList<T>的泛型类型必须是ArrayList<Number>，因此，可以把代码简写为
         * List<Number> list = new ArrayList<>();
         *
         * @泛型接口
         * 除了ArrayList<T>使用了泛型，还可以在接口中使用泛型。例如，Arrays.sort(Object[])可以对任意数组进行排序，但待排序的元素必须实现Comparable<T>这个泛型接口：
         *      public interface Comparable<T> {
         *          返回负数: 当前实例比参数o小
         *          返回0: 当前实例与参数o相等
         *          返回正数: 当前实例比参数o大
         *          int compareTo (T o);
         *      }
         *
         * 可以直接对String数组进行排序：这是因为String本身已经实现了Comparable<String>接口
         */

        /**
         * @小结
         * 使用泛型时，把泛型参数<T>替换为需要的class类型，例如：ArrayList<String>，ArrayList<Number>等；
         * 可以省略编译器能自动推断出的类型，例如：List<String> list = new ArrayList<>();；
         * 不指定泛型参数类型时，编译器会给出警告，且只能将<T>视为Object类型；
         * 可以在接口中定义泛型类型，实现此接口的类必须实现正确的泛型类型。
         */
    }
}

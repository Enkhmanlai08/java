import java.util.Comparator;
import java.util.Scanner;
public class SelectionSortComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = new String[6];
        System.out.println("6 Тэмдэгт мөр оруул : ");
        for (int i = 0; i < 6; i++) {
            strings[i] = scanner.next();
        }
        selectionSort(strings, new MyComparator());
        System.out.println("Эрэмбэлэгдсэн тэмдэгтүүд : ");
        for (String str : strings) {
            System.out.println(str);
        }
    }
    static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            char lastChar1 = str1.charAt(str1.length() - 1);
            char lastChar2 = str2.charAt(str2.length() - 1);
            if (lastChar1 < lastChar2) {
                return -1;
            } else if (lastChar1 > lastChar2)
                return 1;
            return 0;
        }
    }
    public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < list.length; j++) {
                if (comparator.compare(list[j], list[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                E temp = list[i];
                list[i] = list[min];
                list[min] = temp;
            }
        }
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * «адание
 * 1. —клонируйте проект https://github.com/IlyaBikmeev/warAndPieceParse
 * 2. ¬ класс RussianWordsParser добавьте следующий методы:
 * Х	public String mostFrequentWord() Ц возвращает информацию в виде строки о самом часто встречаемом слове. Ќапример Ђплаток Ц встречаетс€ 13064 разаї.
 * Х	public double averageFrequency() Ц возвращает среднюю частоту каждого слова.
 * ”казани€:
 * ƒл€ реализации первого метода вам нужно просто пройтись по MapТу и найти слово с самой большой частотой.
 * ƒл€ реализации второго метода вам нужно просуммировать все значени€ MapТa и поделить эту сумму на размер мапа.
 */
public class Program {
    public static void main(String[] args) {
        //ѕолучаем частоты слов
        RussianWordsParser fileParser = new RussianWordsParser("War and piece.txt");
        fileParser.parse();
        Map<String, Integer> frequency = fileParser.frequency();

        //«аписываем их в файл
        try(FileWriter fileWriter = new FileWriter("text.txt")) {
            for(Map.Entry<String, Integer> entry : frequency.entrySet()) {
                String s = String.format("—лово %s встречаетс€ %d раз\n", entry.getKey(), entry.getValue());
                fileWriter.write(s);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(fileParser.mostFrequentWord());//public String mostFrequentWord() Ц возвращает информацию в виде строки о самом часто встречаемом слове. Ќапример Ђплаток Ц встречаетс€ 13064 разаї.

        int db = (int)fileParser.averageFrequency();
        System.out.printf("—редн€€ чистота слова в тексте равна %d",db);//public double averageFrequency() Ц возвращает среднюю частоту каждого слова.


    }
}

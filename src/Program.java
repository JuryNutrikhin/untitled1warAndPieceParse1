import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * �������
 * 1. ����������� ������ https://github.com/IlyaBikmeev/warAndPieceParse
 * 2. � ����� RussianWordsParser �������� ��������� ������:
 * �	public String mostFrequentWord() � ���������� ���������� � ���� ������ � ����� ����� ����������� �����. �������� ������� � ����������� 13064 ����.
 * �	public double averageFrequency() � ���������� ������� ������� ������� �����.
 * ��������:
 * ��� ���������� ������� ������ ��� ����� ������ �������� �� Map�� � ����� ����� � ����� ������� ��������.
 * ��� ���������� ������� ������ ��� ����� �������������� ��� �������� Map�a � �������� ��� ����� �� ������ ����.
 */
public class Program {
    public static void main(String[] args) {
        //�������� ������� ����
        RussianWordsParser fileParser = new RussianWordsParser("War and piece.txt");
        fileParser.parse();
        Map<String, Integer> frequency = fileParser.frequency();

        //���������� �� � ����
        try(FileWriter fileWriter = new FileWriter("text.txt")) {
            for(Map.Entry<String, Integer> entry : frequency.entrySet()) {
                String s = String.format("����� %s ����������� %d ���\n", entry.getKey(), entry.getValue());
                fileWriter.write(s);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(fileParser.mostFrequentWord());//public String mostFrequentWord() � ���������� ���������� � ���� ������ � ����� ����� ����������� �����. �������� ������� � ����������� 13064 ����.

        int db = (int)fileParser.averageFrequency();
        System.out.printf("������� ������� ����� � ������ ����� %d",db);//public double averageFrequency() � ���������� ������� ������� ������� �����.


    }
}

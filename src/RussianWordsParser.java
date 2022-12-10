import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class RussianWordsParser implements Parser{
    private final String fileName;
    private final Map<String, Integer> wordsFrequency;

    public RussianWordsParser(String fileName) {
        //Можно добавить проверку существования файла fileName
        this.fileName = fileName;
        this.wordsFrequency = new TreeMap<>();

    }

    @Override
    public void parse() {
        if(!wordsFrequency.isEmpty()) {
            //Если мы до этого уже считали частоту слов, то ничего не делаем
            return;
        }
        try(FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String s = null;
            while((s = bufferedReader.readLine()) != null) {
                //        String[] russianWords = s.split("[^А-Яа-я]+");
                String[] russianWords = s.split("[^А-Яа-я]+");

                //             String[] russianWords = s.split("[^A-za-z]+");
                for(String word : russianWords) {
                    if(!word.equals("")) {   //Если слово не является пустой строкой
                        //Слово может либо быть в нашем wordsFrequency, либо не быть.
                        //toLowerCase нужен для преобразования к нижнему регистру
                        //Используем его для того, чтобы например слова "яблоко" и "Яблоко" считались одинаковыми
                        String lowerCaseWord = word.toLowerCase();

                        if(wordsFrequency.get(lowerCaseWord) == null) {
                            //Слово встретилось в первый раз. На данный момент его частота равна единице
                            wordsFrequency.put(lowerCaseWord, 1);
                        }
                        else {
                            //Достаём старую частоту слова. Увеличиваем ее на единицу.
                            int oldFrequency = wordsFrequency.get(lowerCaseWord);
                            wordsFrequency.replace(lowerCaseWord, oldFrequency + 1);
                        }
                    }
                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Map<String, Integer> frequency() {
        return wordsFrequency;
    }
//................................................................................
    //public String mostFrequentWord() – возвращает информацию в виде строки о самом часто встречаемом слове. Например «платок – встречается 13064 раза».

    public String mostFrequentWord(){
        String maxKey ="";
        int maxValue  = 0;

        for(Map.Entry<String,Integer> entry : wordsFrequency.entrySet() ){
            if(entry.getValue()>maxValue){
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        String s= String.format("Слово \"%s\" чаще все встречается в тексте %d раз ",maxKey,maxValue);
        return s;
    }
    //.........................................................................

    //public double averageFrequency() – возвращает среднюю частоту каждого слова.

    public double averageFrequency(){
        double averagsSize = 0;
        double averagsNum=0;

        averagsSize= wordsFrequency.size();
        for(Map.Entry<String,Integer> entry: wordsFrequency.entrySet()){
            averagsNum+=entry.getValue();
        }

        double averagsSum= averagsNum/averagsSize;
        return averagsSum;
//...............................................................................
    }
}

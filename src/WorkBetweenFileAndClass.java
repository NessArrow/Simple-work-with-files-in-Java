import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class WorkBetweenFileAndClass {
    /**
     * Посимвольно достает по строке из файла и отправляет в метод humanFromString,
     * который возвращает объект класса Human
     * затем объект добавляется в лист объектов и возвращается ретурном
     * @param fileName имя файла
     * @return лист объектов класса Human
     */
    public static List<Human> humansFromFileToList(String fileName){
        List <Human> humans = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
//                System.out.printf("%c %d\n", c, c);
                if (c == 10) {
                    if (!tmp.isEmpty()) humans.add(humanFromString(tmp.toString()));
                    tmp = new StringBuilder();
                } else {
                    tmp.append((char) c);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return humans;
    }

    /**
     * Делит строку на элементы по разделителю, создает, назначает параметры и возвращает объект класса Человек
     * @param line строка, которую нужно распарсить по параметрам объекта
     * @return объект класса Human
     */
    public static Human humanFromString (String line) {
        String[] partsOfHuman = new String[4];
        partsOfHuman = line.split("[\\s\t]+");
        return new Human(partsOfHuman[0], Integer.parseInt(partsOfHuman[1]),partsOfHuman[2],strToProfession(partsOfHuman[3]));
    }

    /**
     * Преобразовывает строку в объект перечисления
     * @param line профессия в виде строки
     */
    public static Profession strToProfession(String line) {
        Profession profession;
        switch (line) {
            case "doctor" -> profession = Profession.DOCTOR;
            case "engineer" -> profession = Profession.ENGINEER;
            case "teacher" -> profession = Profession.TEACHER;
            case "policeman" -> profession = Profession.POLICEMAN;
            case "lawyer" -> profession = Profession.LAWYER;
            case "actor" -> profession = Profession.ACTOR;
            default -> profession = Profession.NOPE;
        }
        return profession;
    }

    /**
     * Дозаписывает строку в конец файла
     * @param line строка, которую нужно записать в файл
     * @param fileName имя файла
     */
    public static void writeInFileFromString(String line, String fileName) {
        try (FileWriter writer = new FileWriter(fileName,true)){
            writer.write(line);
            writer.append('\n');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Посимвольно читает файл и печатает в консоль
     * @param fileName имя файла
     */
    public static void readFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            while ((c = fileReader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Ищет слово в файле
     * @param fileName имя файла
     * @param word искомое слово
     * @return true, если слово найдено
     */
    public static boolean findWord(String fileName, String word) {
        boolean result = false;
        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (c != 9 && c != 10) {
                    tmp.append((char) c);
                } else {
                    if (tmp.toString().equals(word)){
                        result = true;
                        break;
                    }
                    tmp = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Принтит строку, в которой находится искомое слово в файле, в противном случае печатает "Not found"
     * Печатает последнее совпадение
     */
    public static String findLineByWord(String word, String fileName) {
        String result = "Not found";
        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (c != 10) {
                    tmp.append((char)c);
                } else {
                    if (tmp.indexOf(word) != -1) {
                        result = tmp.toString();
                        break;
                    }
                    tmp = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Возвращает лист строк, в которых находится искомое слово в файле
     */
    public static List <String> listOfMatches (String word, String fileName) {
        List <String> result = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (c != 10) {
                    tmp.append((char)c);
                } else {
                    if (tmp.indexOf(word) != -1) {
                        result.add(tmp.toString());
                    }
                    tmp = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     * Тест класса, который может запоминать указатель и делать запись в нужное место файла
     * @param filename имя файла, в который ведется запись
     * @param findWord слово, после которого сделается запись временной переменной
     */
    public static void workWithClassRandomAccessFile(String filename, String findWord) {
        String tmp = "QWERTY";
        int a;
        long point = 0;
        StringBuilder sb = new StringBuilder();
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")){
            while ((a = file.read()) != -1) {
                if (a != 32 && a != 10) {
                    sb.append((char) a);
                } else {
                    if (sb.toString().equals(findWord)) {
                        point = file.getFilePointer();
                        break;
                    }
                    sb = new StringBuilder();
                }
            }
            file.seek(point);
            file.write("CHTOTO".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

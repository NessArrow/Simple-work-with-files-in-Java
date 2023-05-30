import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String text = "This is a some text, damn";
        String text1 = "This also text";
        String fileName = "src/one.txt";
        String file2Name = "src/two.txt";
//        writeStringInFileWithFileOutputStream(text, fileName);
//        readDataFromFileWithFileInputStream(fileName);
//        readFromOneAndWriteToTwo(fileName,file2Name);
//        outputInFileFileWriter(fileName, text);
//        fileReaderBySymbols(fileName);
//        fileReaderWithBuffer(fileName);
        fromConsoleToFile(file2Name);
    }
    public static void fromConsoleToFile(String filename) {
        // работает только при запуске программы из командной строки
        Console console = System.console();
        if (console != null) {
            String line = console.readLine("Vvedite text: ");
            outputInFileFileWriter(filename, line);
        }
    }
    public static void fileReaderWithBuffer(String fileName) {
        try (FileReader reader = new FileReader(fileName)){
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
//                System.out.println(c);
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void fileReaderBySymbols(String fileName) {
        try (FileReader reader = new FileReader(fileName)){
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void outputInFileFileWriter(String fileName, String text) {
        try(FileWriter writer = new FileWriter(fileName, true)){
//            writer.append('\n');
            writer.write(text);
            writer.append('\n');
//            writer.append('E');
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void writeStringInFileWithFileOutputStream(String text, String file) {
        try (FileOutputStream fos = new FileOutputStream(file)){
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
            System.out.println("The file was been written");
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void readDataFromFileWithFileInputStream(String fileName) {
        try(FileInputStream fin = new FileInputStream(fileName)){
            int i;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readFromOneAndWriteToTwo(String file1, String file2) {
        try (FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2)){
            byte[] buffer = new byte[256];
            int count;
            while ((count = fis.read(buffer)) != -1) {
                fos.write(buffer,0,count);
            }
            System.out.println("File has been written");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
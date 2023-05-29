import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String text = "This is a some text, damn";
        String text1 = "This also text";
        String fileName = "src/one.txt";
        String file2Name = "src/two.txt";
//        writeStringInFileWithFileOutputStream(text, fileName);
//        readDataFromFileWithFileInputStream(fileName);
        readFromOneAndWriteToTwo(fileName,file2Name);
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
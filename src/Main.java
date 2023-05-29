import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String text = "This is a some text, damn";
        String text1 = "This also text";
        writeStringInFileWithFileOutputStream(text, "one.txt");
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
}
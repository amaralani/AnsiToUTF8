import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        if (args.length > 0 && "no-ui".equals(args[0])) {
            try {
                CustomFileConverter.createFile(args[1], args[1].substring(0, args[1].length() - 4) + "UTF8" + args[1].substring(args[1].length() - 4));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            UI m = new UI();
            m.setLocation(200, 200);
            m.setSize(300, 100);
            m.setVisible(true);
        }
    }
}

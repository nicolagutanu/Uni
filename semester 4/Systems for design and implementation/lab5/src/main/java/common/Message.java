package common;

import java.io.*;

public class Message {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    //because in windows the end on the line is not \n but \r\n
    public static final String OK = "ok";
    public static final String ERROR = "error";

    private String header;
    private String body;

    public Message() {}

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message:" + header + '\n' + body + '\n';
    }

    public void readFrom(InputStream inStream) throws IOException {
        var br = new BufferedReader(new InputStreamReader(inStream));
        header = br.readLine();
        body = br.readLine();
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}

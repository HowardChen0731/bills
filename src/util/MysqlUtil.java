package util;

import java.io.*;

public class MysqlUtil {

    // ±¸·Ý
    public static void backup(String mysqlPath, String backupFile) throws IOException {
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s -hlocalhost -P%d %s -r \"%s\"";
        String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port, DBUtil.database, backupFile);
        Runtime.getRuntime().exec(command);
    }

    // »Ö¸´
    public static void recover(String mysqlPath, String recoverFile) {
        try {
            String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s";
            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.database);

            Process p = Runtime.getRuntime().exec(command);
            OutputStream out = p.getOutputStream();
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFile), "utf8"));

            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }

            outStr = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

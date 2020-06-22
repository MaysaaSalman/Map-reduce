package mapreduce;

import dfs.HDFSFileSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputSplit {
    public static ArrayList<String> getInputSplit(long blockId) {
        ArrayList<String> stringList = new ArrayList<>();

        try {


            BufferedReader reader = new BufferedReader(new FileReader(HDFSFileSystem.getBlocksLocations().get(blockId)));
            Pattern pattern = Pattern.compile("[a-zA-Z]+");

            Matcher matcher;
            String str = reader.readLine();
            while (str != null) {
                if (!str.equals("")) {
                    matcher = pattern.matcher(str);
                    while (matcher.find()) {
                        String word = matcher.group();
                        stringList.add(word);
                    }
                }
                str = reader.readLine();
            }
        } catch (IOException e) {

        }
        return stringList;

    }
}

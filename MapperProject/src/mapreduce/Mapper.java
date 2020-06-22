package mapreduce;

import injection.CustomAction;
import injection.CustomActionBuilder;


public class Mapper<KEYIN, VALUEIN> {

    private static Code code;

    public static void map() {
        System.out.println("First");
        String s = "                                                                  "
                + " " +
                "for (TaskInfo taskInfo:TaskTracker.getTasks()) {                                 "
                + "      Context context=new Context(taskInfo);                                  "
                + "     ArrayList<String> keys= InputSplit.getInputSplit(taskInfo.getBlockId()); "
                + "                                                                              "
                + "      for (String keyIn:keys                                                  "
                + "           ) {                                                                "
                + "                                                                              "
                + "                                                                              ";


        String s2 = " "
                + "          context.write(record);                                              "
                + "                                                                              "
                + "      }                                                                       "
                + "      context.writeToDisk();                                                  "
                + "  }                                                                           "
                + "                                                  ";


        CustomActionBuilder builder = new CustomActionBuilder();
        CustomAction maper = builder.newAction("mapping").withCode(s + code.getCode() + s2)
                .build();
        maper.execute();

        System.out.println("From Map function");
        return;
    }


    public static void setCode(Code code) {
        Mapper.code = code;
    }
}

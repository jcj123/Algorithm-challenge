package sf.sgs.Algorithm;

import com.google.gson.Gson;

public class FirstCode {


    private static Gson gson;

    private static TestBean  getEnvironment() {
        String result = HttpHelper.post("http://10.2.5.64/test", "{\"name\":\"某不知名炮灰队\"}");
        System.out.println(result);
        gson = new Gson();
        TestBean testBean = gson.fromJson(result,TestBean.class);
        System.out.println(testBean.toString());
        return testBean;
    }
    public static ResultBean getControl(String id) {
        String controlUrl = "http://10.2.5.64/test/"+id+"/move";
        ControlBean controlBean = new ControlBean("U");
        String controlJson = gson.toJson(controlBean);
        System.out.println("controlJson:"+controlJson);
        String result = HttpHelper.post(controlUrl, controlJson);
        System.out.println("controlResult :"+result);
        ResultBean resultBean = gson.fromJson(result,ResultBean.class);
        System.out.println(resultBean.toString());
        return resultBean;
    }

    public static void getReplay(String id) {
        String replayUrl = "http://10.2.5.64/test/replays/"+id;
        String repalyJson = HttpHelper.get(replayUrl);
        System.out.println("repalyJson :"+repalyJson);
    }
    public static void main(String[] args) {
        TestBean resultTestBean = getEnvironment();
        ResultBean controlBean = getControl(resultTestBean.getId());
        getReplay(resultTestBean.getId());
    }

}

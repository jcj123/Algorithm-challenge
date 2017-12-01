package sf.sgs.Algorithm;

import com.google.gson.Gson;

public class FirstCode {
    public static void main(String[] args) {
        System.out.println("hello rookie");
        String post = HttpHelper.post("http://10.2.5.64/test", "{\"name\":\"某不知名炮灰队\"}");
        System.out.println(post);
        Gson gson = new Gson();
        TestBean testBean = gson.fromJson(post,TestBean.class);
        System.out.println(testBean.toString());
    }
}

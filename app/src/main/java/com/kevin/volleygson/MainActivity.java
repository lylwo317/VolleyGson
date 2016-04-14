package com.kevin.volleygson;

import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.kevin.volleygson.net.NetResponseListener;
import com.kevin.volleygson.request.BaseRequest;
import com.kevin.volleygson.response.IpResponse;
import com.kevin.volleygson.response.WetherResponse;
import com.kevin.volleygson.utils.RequestUtil;

import java.net.InetAddress;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private EditText mEtIpInput;
    private Button mBtQuery;
    private TextView mTvJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtIpInput = (EditText) findViewById(R.id.et_ip_input);
        mBtQuery = (Button) findViewById(R.id.bt_query);
        mTvJson = (TextView) findViewById(R.id.tv_json);

        mBtQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtIpInput.getText().toString())) {
                    sendRequest(mEtIpInput.getHint().toString());
                } else {
                    sendRequest(mEtIpInput.getText().toString());
                }
            }
        });
    }

    /**
     * 判断是否为合法IP
     * @return the ip
     */
    public boolean isboolIp(String ipAddress)
    {
        if(ipAddress.length() < 7 || ipAddress.length() > 15 || "".equals(ipAddress))
        {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(ipAddress);

        

        return mat.find();
    }

    private void sendRequest(String ip) throws InvalidParameterException{

        mTvJson.setText("");


        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setTag(TAG);
        baseRequest.setUrl("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="+ip);

        RequestUtil.getInstance(getApplicationContext()).queryPageInfo(baseRequest, IpResponse.class, new NetResponseListener<IpResponse>() {
            @Override
            public void onResponseSuccess(IpResponse response) {
                mTvJson.setText(response.toString());
            }

            @Override
            public boolean onResponseFailed() {
                return false;
            }

            @Override
            public boolean onResponseError(VolleyError volleyError) {
                return false;
            }

            @Override
            public void onFinally() {

            }
        });
    }
}

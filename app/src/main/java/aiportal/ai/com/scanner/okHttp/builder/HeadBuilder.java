package aiportal.ai.com.scanner.okHttp.builder;


import aiportal.ai.com.scanner.okHttp.OkHttpUtils;
import aiportal.ai.com.scanner.okHttp.request.OtherRequest;
import aiportal.ai.com.scanner.okHttp.request.RequestCall;

/**
 * Created by song on 4/8/2016.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}

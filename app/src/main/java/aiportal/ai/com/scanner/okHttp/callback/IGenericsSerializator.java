package aiportal.ai.com.scanner.okHttp.callback;

/**
 * Created by song on 4/8/2016.
 */
public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}

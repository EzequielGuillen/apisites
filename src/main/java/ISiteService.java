import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ISiteService {

    public String getSites() throws UnknownHostException, IOException;

    public String getCategories(String id) throws SocketException,IOException;

}

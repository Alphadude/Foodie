package io.github.alphadude.jambhangout.Network;

import io.github.alphadude.jambhangout.Models.Login;
import io.github.alphadude.jambhangout.Models.Response;
import io.github.alphadude.jambhangout.Models.User;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by root on 8/24/17.
 */

public interface JambHangoutInterface {
    @POST (" ")
    Observable<Response> register(@Body User user);

    @POST("")
    Observable<Response> login(@Body Login userLogin);


}

package io.github.alphadude.jambhangout.Network;

import io.github.alphadude.jambhangout.Models.Login;
import io.github.alphadude.jambhangout.Models.Response;
import io.github.alphadude.jambhangout.Models.User;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;



public interface JambHangoutInterface {
    @POST ("/newuser")
    Observable<Response> register(@Body User user);

    @POST(" ")
    Observable<Response> login(@Body Login userLogin);


}

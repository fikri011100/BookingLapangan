package santriprogrammer.com.bookinglapangan.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("user_register.php")
    Call<PojoRegister> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username,
            @Field("no_telp") String no_telp,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("user_login.php")
    Call<PojoLogin> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("getbooking.php")
    Call<PojoTicket> getTicket(
            @Field("booking_user_email") String bookingUserEmail
    );

    @FormUrlEncoded
    @POST("getBookingAdmin.php")
    Call<PojoBookingAdmin> getBookingAdmin(
            @Field("booking_user_email") String bookingUserEmail
    );

    @FormUrlEncoded
    @POST("user.php")
    Call<PojoUser> getUser(
            @Field("email") String user_id
    );

    @FormUrlEncoded
    @POST("deleteTicket.php")
    Call<PojoDeleteTicket> deleteTicket(
            @Field("booking_id") String bookingId,
            @Field("booking_status") String bookingStatus
    );

    @FormUrlEncoded
    @POST("gettransferbook.php")
    Call<PojoTransferBooking> getTransferBooking(
            @Field("id_booking") String bookingId
    );

    @FormUrlEncoded
    @POST("upload_booking.php")
    Call<PojoUploadBooking> postBooking(
            @Field("booking_user_email") String user_id,
            @Field("booking_place") String booking_place,
            @Field("booking_category") String booking_category,
            @Field("booking_hour") String booking_hour,
            @Field("booking_hour_until") String booking_hour_until,
            @Field("booking_dates") String booking_dates,
            @Field("booking_price") String booking_price,
            @Field("booking_status") String booking_status
    );

    @GET("show_lapangan.php")
    Call<PojoLapangan> getLapangan();

    @GET("getNews.php")
    Call<PojoNews> getNews();

    @GET("getAllBooking.php")
    Call<PojoAllBooking> getAllBooking();

}

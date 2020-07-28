package com.anggitlearn.retrofitonkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.anggitlearn.retrofitonkotlin.data.UserResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NetworkConfig().getService()
            .getUsers()
            .enqueue(object : Callback<List<UserResult>> {
                override fun onFailure(call: Call<List<UserResult>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<UserResult>>,
                    response: Response<List<UserResult>>
                ) {
                    rvUser.adapter = UsersAdapter(response.body())
                }
            })
    }
}
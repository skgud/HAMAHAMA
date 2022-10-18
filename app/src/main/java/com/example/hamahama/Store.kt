package com.example.hamahama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hamahama.databinding.BookBinding
import com.example.hamahama.databinding.StoreBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class Store : AppCompatActivity() {
    //뷰바인딩
    private var mBinding: StoreBinding? = null
    private val binding get() = mBinding!!
    //Firestore 인스턴스 선언
    val db = FirebaseFirestore.getInstance()
    val fbdb = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = StoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coin = binding.tvCoin
        fbdb.collection("member")
            .get()
            .addOnCompleteListener{ task ->

                var afound =false //데이터 찾지 못했을 때

                if (task.isSuccessful) {
                    for(i in task.result!!){
                        if(i.id == coin.toString()){
                            val  coin = i.data["coin"]

                            afound=true //찾았다

                            break
                        }
                    }
                }

                if (!afound){
                    coin.text = "can not found"
                }else{ //오류 발생시
                        coin.text = "error"
                }

            }

    }
}
















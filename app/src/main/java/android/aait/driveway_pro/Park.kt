package android.aait.driveway_pro

import java.io.Serializable

data class Park (val _id:String,
                 val plate_number:String,
                 val slot_id:String,
                 val ticket_status:String)
data class Exit(val _id: String,
                      val plate_number:String,
                      val slot_id:String,
                      val ticket_status:String,
                      val park_at:String,
                      val exit_at:String,
                      val price_per_hour:Int,
                      val total_price:Double): Serializable
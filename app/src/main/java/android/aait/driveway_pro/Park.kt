package android.aait.driveway_pro

data class Park (val _id:String,
                 val plate_number:String,
                 val slot_id:String,
                 val ticket_status:String)

data class Exit(val ticket_status:String)

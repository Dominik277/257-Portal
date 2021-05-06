package Portal.adapter


import Portal.a257.databinding.JedanRedZabavaBinding
import Portal.fragmenti.fragmenti.ZabavaFragmentDirections
import Portal.model.ZabavaTable
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import java.text.SimpleDateFormat
import java.util.*

class ZabavaAdapter(options: FirestoreRecyclerOptions<ZabavaTable>) :
    FirestoreRecyclerAdapter<ZabavaTable, ZabavaAdapter.ZabavaViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZabavaViewHolder {
        return ZabavaViewHolder(
            JedanRedZabavaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ZabavaViewHolder, position: Int, model: ZabavaTable) {
        val sdf = SimpleDateFormat("dd.MM.yyyy. HH:mm")
        val currentDate = sdf.format(Date())

        holder.vrijeme.text = currentDate
        holder.naslov.text = model.zabavaNaslov

        holder.binding.cardViewZabava.setOnClickListener { v: View ->
            val data = ZabavaTable(model.zabavaNaslov,model.zabavaClanak)
            val action = ZabavaFragmentDirections.actionZabavaNavDrawerToDetailZabava(data)
            v.findNavController().navigate(action)
        }
    }

    class ZabavaViewHolder(val binding: JedanRedZabavaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var naslov = binding.naslov
        var vrijeme = binding.vrijeme
    }
}

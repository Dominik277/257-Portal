package Portal.adapter

import Portal.a257.databinding.JedanRedPoljoprivredaBinding
import Portal.fragmenti.fragmenti.PoljoprivredaFragmentDirections
import Portal.model.PoljoprivredaTable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import java.text.SimpleDateFormat
import java.util.*

class PoljoprivredaAdapter(options: FirestoreRecyclerOptions<PoljoprivredaTable>) :
    FirestoreRecyclerAdapter<PoljoprivredaTable, PoljoprivredaAdapter.PoljoprivredaViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoljoprivredaViewHolder {
        return PoljoprivredaViewHolder(
            JedanRedPoljoprivredaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PoljoprivredaViewHolder, position: Int, model: PoljoprivredaTable) {
        val sdf = SimpleDateFormat("dd.MM.yyyy. HH:mm")
        val currentDate = sdf.format(Date())

        holder.vrijeme.text = currentDate
        holder.naslov.text = model.poljoprivredaNaslov
        holder.clanak.text = model.poljoprivredaClanak

        holder.binding.cardViewPoljoprivreda.setOnClickListener { v: View ->
            val data = PoljoprivredaTable(model.poljoprivredaNaslov,model.poljoprivredaClanak)
            val action = PoljoprivredaFragmentDirections.actionPoljoprivredaNavDrawerToPoljoprivredaDetail(data)
            v.findNavController().navigate(action)
        }

    }

    class PoljoprivredaViewHolder(val binding: JedanRedPoljoprivredaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var naslov = binding.naslov
        var clanak = binding.clanak
        var vrijeme = binding.vrijeme
    }
}
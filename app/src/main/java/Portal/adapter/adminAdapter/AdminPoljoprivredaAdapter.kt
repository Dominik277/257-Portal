package Portal.adapter.adminAdapter

import Portal.a257.R
import Portal.database.table.PoljoprivredaTable
import Portal.fragmenti.admin.odobri.AdminOdobriPoljoprivredaDirections
import Portal.fragmenti.fragmenti.PoljoprivredaFragmentDirections
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.jedan_red_poljoprivreda.view.*

class AdminPoljoprivredaAdapter : RecyclerView.Adapter<AdminPoljoprivredaAdapter.ViewHolder>() {

    private var poljoprivredaList = emptyList<PoljoprivredaTable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminPoljoprivredaAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.jedan_red_poljoprivreda, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminPoljoprivredaAdapter.ViewHolder, position: Int) {
        val currentItem = poljoprivredaList[position]
        holder.itemView.textViewPoljoprivredaNaslov.text = currentItem.poljoprivredaNaslov
        holder.itemView.textViewPoljoprivredaVrijeme.text = currentItem.poljoprivredaVrijeme

        holder.itemView.cardViewPoljoprivreda.setOnClickListener {
            val action =
                AdminOdobriPoljoprivredaDirections.actionAdminOdobriPoljoprivredaToAdminPotvrdiPoljoprivreda(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return poljoprivredaList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData(poljoprivreda: List<PoljoprivredaTable>) {
        this.poljoprivredaList = poljoprivreda
        notifyDataSetChanged()
    }

}
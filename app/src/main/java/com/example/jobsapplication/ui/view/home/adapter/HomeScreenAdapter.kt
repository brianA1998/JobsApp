package com.example.jobsapplication.ui.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jobsapplication.core.BaseViewHolder
import com.example.jobsapplication.data.model.Publication
import com.example.jobsapplication.databinding.PublicationsItemViewBinding

class HomeScreenAdapter(private val publicationList: List<Publication>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            PublicationsItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenViewHolder(itemBinding, parent.context)
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HomeScreenViewHolder -> holder.bind(publicationList[position])
        }
    }

    override fun getItemCount(): Int = publicationList.size

    private inner class HomeScreenViewHolder(
        val binding: PublicationsItemViewBinding,
        val context: Context
    ) : BaseViewHolder<Publication>(binding.root) {
        override fun bind(item: Publication) {
            setupPublicationImage(item)
            setupPublicationDescription(item)
        }

        /**
         * Carga la description de cada publicacion
         *
         * @param item un elemento de la lista de publicaciones
         */
        private fun setupPublicationDescription(item: Publication) {
            if (item.publication_description.isEmpty()) {
                binding.publicationDescription.visibility = View.GONE
            } else {
                binding.publicationDescription.visibility = View.VISIBLE
            }
        }

        /**
         * Carga la imagen de cada publicacion
         *
         * @param item un elemento de la lista de publicaciones
         */
        private fun setupPublicationImage(item: Publication) {
            Glide.with(context).load(item.publication_image).centerCrop()
                .into(binding.publicationImage)
        }


    }


}
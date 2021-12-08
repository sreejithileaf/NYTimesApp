package com.nyt.nytimes.ui.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nyt.nytimes.R
import com.nyt.nytimes.databinding.FragNewsdetailsBinding
import com.squareup.picasso.Picasso


class NewsDetailsFragment : Fragment() {

    private var _binding: FragNewsdetailsBinding? = null
    private val args: NewsDetailsFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragNewsdetailsBinding.inflate(inflater, container, false)
        return binding.apply {

            tvNews.text = args.title
            tvDesc.text = args.content
            tvDate.text = args.date
            tvAuthor.text = args.author
            context?.let {

             try {
                 Picasso.with(requireContext())
                     .load(args.image)
                     .placeholder(R.drawable.placeholder)
                     .error(R.drawable.placeholder)
                     .into(ivAvatar)
             }catch (e:Exception){
                 Picasso.with(requireContext())
                     .load(R.drawable.placeholder)
                     .into(ivAvatar)
             }

            }
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
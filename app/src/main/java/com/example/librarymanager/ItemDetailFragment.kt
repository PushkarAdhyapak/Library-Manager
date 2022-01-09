package com.example.librarymanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.librarymanager.data.Item
import com.example.librarymanager.data.getFormattedPrice
import com.example.librarymanager.databinding.FragmentItemDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder




class ItemDetailFragment : Fragment() {
    private val navigationArgs: ItemDetailFragmentArgs by navArgs()
    lateinit var item: Item

    private val viewModel: LibraryViewModel by activityViewModels {
        LibraryViewModelFactory(
            (activity?.application as LibraryApplication).database.itemDao()
        )
    }

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun bind(item: Item) {
        binding.apply {
            bookName.text = item.itemName
            bookPrice.text = item.getFormattedPrice()
            itemCount.text = item.quantityInStock.toString()
            sellItem.isEnabled = viewModel.isStockAvailable(item)
            sellItem.setOnClickListener { viewModel.sellItem(item) }
            deleteItem.setOnClickListener { showConfirmationDialog() }
            editItem.setOnClickListener { editItem() }
        }
    }


    private fun editItem() {
        val action = ItemDetailFragmentDirections.actionItemDetailFragmentToAddItemFragment(
            getString(R.string.edit_fragment_title),
            item.id
        )
        this.findNavController().navigate(action)
    }


    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }


    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.itemId

        viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
            item = selectedItem
            bind(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
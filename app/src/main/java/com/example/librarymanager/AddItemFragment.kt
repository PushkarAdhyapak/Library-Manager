package com.example.librarymanager

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.librarymanager.data.Item
import com.example.librarymanager.databinding.FragmentAddItemBinding


class AddItemFragment : Fragment() {


    private val viewModel: LibraryViewModel by activityViewModels {
        LibraryViewModelFactory(
            (activity?.application as LibraryApplication).database
                .itemDao()
        )
    }
    private val navigationArgs: ItemDetailFragmentArgs by navArgs()

    lateinit var item: Item


    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.itemName.text.toString(),
            binding.itemPrice.text.toString(),
            binding.itemCount.text.toString(),
        )
    }


    private fun bind(item: Item) {
        val price = "%.2f".format(item.itemPrice)
        binding.apply {
            itemName.setText(item.itemName, TextView.BufferType.SPANNABLE)
            itemPrice.setText(price, TextView.BufferType.SPANNABLE)
            itemCount.setText(item.quantityInStock.toString(), TextView.BufferType.SPANNABLE)
            saveAction.setOnClickListener { updateItem() }
        }
    }


    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItem(
                binding.itemName.text.toString(),
                binding.itemPrice.text.toString(),
                binding.itemCount.text.toString(),
            )
            val action = AddItemFragmentDirections.actionAddItemFragmentToItemListFragment()
            findNavController().navigate(action)
        }
    }


    private fun updateItem() {
        if (isEntryValid()) {
            viewModel.updateItem(
                this.navigationArgs.itemId,
                this.binding.itemName.text.toString(),
                this.binding.itemPrice.text.toString(),
                this.binding.itemCount.text.toString()
            )
            val action = AddItemFragmentDirections.actionAddItemFragmentToItemListFragment()
            findNavController().navigate(action)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.itemId
        if (id > 0) {
            viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
                item = selectedItem
                bind(item)
            }
        } else {
            binding.saveAction.setOnClickListener {
                addNewItem()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}
/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.databinding.FragmentOverviewBinding
import com.example.android.marsrealestate.databinding.GridViewItemBinding

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class OverviewFragment : Fragment() {

	/**
	 * Lazily initialize our [OverviewViewModel].
	 */
	private val viewModel: OverviewViewModel by lazy {
		ViewModelProvider(this).get(OverviewViewModel::class.java)
	}

	/**
	 * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
	 * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
	 */
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
//		val binding = FragmentOverviewBinding.inflate(inflater)
		val binding = GridViewItemBinding.inflate(inflater)

		// Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
		binding.lifecycleOwner = this

		// Giving the binding access to the OverviewViewModel
		binding.viewModel = viewModel

		// The usage of an interface lets you inject your own implementation
		val menuHost: MenuHost = requireActivity()
		addMenu(menuHost)

		return binding.root
	}

	private fun addMenu(menuHost: MenuHost) {
		// Add menu items without using the Fragment Menu APIs
		// Note how we can tie the MenuProvider to the viewLifecycleOwner
		// and an optional Lifecycle.State (here, RESUMED) to indicate when
		// the menu should be visible
		menuHost.addMenuProvider(object : MenuProvider {
			override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
				// Add menu items here
				menuInflater.inflate(R.menu.overflow_menu, menu)
			}

			override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
				// Handle the menu selection
				return when (menuItem.itemId) {
					else -> false
				}
			}
		}, viewLifecycleOwner, Lifecycle.State.RESUMED)
	}
}

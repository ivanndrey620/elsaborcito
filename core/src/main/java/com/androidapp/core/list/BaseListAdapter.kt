package com.androidapp.core.list

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.core.BR
import com.androidapp.core.extension.bind
import com.androidapp.core.viewModel.ListItemViewModel

/**
 *
 * T: Model Class
 * VH: BaseViewHolder
 * VDB: ViewDataBinding
 */
abstract class BaseListAdapter<T, VM : ListItemViewModel, VH : BaseViewHolder<VDB, VM>, VDB : ViewDataBinding>
    : RecyclerView.Adapter<VH>() {

    open val data: ObservableArrayList<T> = ObservableArrayList()

    open val copyData: ObservableArrayList<T> = ObservableArrayList()

    open var itemClickListener: ItemListClickListener<T>? = null

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewDataBinding = parent.bind<VDB>(layoutRes(viewType), attachToParent = false)

        return getViewHolder(viewDataBinding, viewDataBinding.root, viewType)
    }

    override fun onBindViewHolder(viewHolder: VH, itemPosition: Int) {
        val item: T = data[itemPosition]
        setItemViewModel(viewHolder, item, itemPosition)
        viewHolder.executePendingBindings()

        itemClickListener?.let { listener ->
            viewHolder.itemView.setOnClickListener { listener.onItemListClickListener(item, itemPosition) }
        }
    }


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * ## Item ViewModel LifeCycle
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * ## External Actions
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    open fun setData(newData: List<T>) {
        this.data.clear()
        this.data.addAll(newData)
        this.notifyDataSetChanged()
    }

    open fun remove(item: T) {
        val index = this.data.indexOf(item)
        if (index != -1 && index < this.data.size) {
            this.data.removeAt(index)
            this.notifyItemRemoved(index)
        }
    }

    open fun addItem(item: T) {
        this.data.add(0, item)
        this.notifyItemInserted(0)
    }

    open fun getDataList() = data

    open fun clearData() = this.data.clear()

    open fun setItemViewModel(holder: VH, item: T, position: Int) {
        holder.setViewModel(BR.viewModel, getViewModel(holder, item, position))
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * ## Abstract Methods
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @LayoutRes
    abstract fun layoutRes(viewType: Int): Int

    abstract fun getViewHolder(viewDataBinding: ViewDataBinding, view: View, viewType: Int): VH

    abstract fun getViewModel(holder: VH, item: T, position: Int): VM

}
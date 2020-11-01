package com.mariotm.ddamq.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mariotm.ddamq.R;
import com.mariotm.ddamq.clases.vo.AvatarVo;
import com.mariotm.ddamq.clases.vo.CategoriaVo;

import java.util.List;

public class AdaptadorCategorias extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolderCategorias> {

    List<CategoriaVo> listaCategorias;
    View view;

    public AdaptadorCategorias(List<CategoriaVo> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    @Override
    public ViewHolderCategorias onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_categorias,
                viewGroup, false);
        return new ViewHolderCategorias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategorias viewHolderCategorias, int i) {
        viewHolderCategorias.txtCategoria.setText(listaCategorias.get(i).getCategoria());
        viewHolderCategorias.txtDescripcion.setText(listaCategorias.get(i).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class ViewHolderCategorias extends RecyclerView.ViewHolder {

        TextView txtCategoria, txtDescripcion;

        public ViewHolderCategorias(View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
        }
    }
}

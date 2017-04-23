/*
 * Copyright 2017 Michael Bakogiannis, Open Source Project
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
 */

package com.bakoproductions.pokemoncleanexample.presentation.components.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.presentation.components.utils.AvatarLoader;

import java.util.ArrayList;

/**
 * Created by Michael on 16/4/2017.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_POKEMON = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    private ArrayList<Pokemon> pokemonList;
    private int loadingIndex = - 1;
    private PokemonClickListener listener;

    public PokemonListAdapter(ArrayList<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingHolder(
                    LayoutInflater
                            .from(parent.getContext())
                            .inflate(R.layout.row_loading, parent, false)
            );
        } else {
            return new PokemonHolder(
                    LayoutInflater
                            .from(parent.getContext())
                            .inflate(R.layout.row_pokemon, parent, false)
            );
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_POKEMON) {
            final PokemonHolder holder = (PokemonHolder) viewHolder;
            Pokemon pokemon = pokemonList.get(position);
            pokemon.getId();
            AvatarLoader.loadAvatar(holder.avatar, pokemon.getAvatarUrl());
            holder.name.setText(pokemon.getNameFormatted());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onPokemonClicked(pokemonList.get(position));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Pokemon pokemon = pokemonList.get(position);

        if (pokemon instanceof LoadingPokemonModel) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_POKEMON;
        }
    }

    public void setClickListener(PokemonClickListener listener) {
        this.listener = listener;
    }

    public void addLoading() {
        pokemonList.add(new LoadingPokemonModel());
        loadingIndex = pokemonList.size() - 1;
        notifyItemInserted(loadingIndex);
    }

    public void removeLoading() {
        if (loadingIndex != -1){
            pokemonList.remove(loadingIndex);
            notifyItemRemoved(loadingIndex);
            loadingIndex = -1;
        }
    }

    public void addMorePokemon(ArrayList<Pokemon> pokemonList) {
        int start = this.pokemonList.size();
        int size = pokemonList.size();

        this.pokemonList.addAll(pokemonList);
        notifyItemRangeInserted(start, size);
    }

    public class PokemonHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;

        public PokemonHolder(View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.pokemonAvatar);
            name = (TextView) itemView.findViewById(R.id.pokemonName);
        }
    }

    public class LoadingHolder extends RecyclerView.ViewHolder {
        public LoadingHolder(View itemView) {
            super(itemView);
        }
    }

    public interface PokemonClickListener {
        void onPokemonClicked(Pokemon pokemon);
    }

    private class LoadingPokemonModel extends Pokemon {}
}

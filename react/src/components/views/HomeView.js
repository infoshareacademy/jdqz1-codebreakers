import React from 'react'

import SearchBar from '../SearchBar'
import DataFetcher from "../DataFetcher/DataFetcher";
import Viewed from "../Viewed";
import Categories from "../Categories";

const HomeView = () => {
  const initialState = [];
  return (
    <div>
      <DataFetcher
        dataUrl={'/data/products.json'}
        component={SearchBar}
        propName="searchedProducts"
      />
      <DataFetcher
        dataUrl={'/data/products.json'}
        component={Categories}
        propName="categoriesProducts"
        propCategories={initialState}
        propInitialName="initialState"
      />
      <DataFetcher
        dataUrl={'/data/products.json'}
        component={Viewed}
        propName="searchedProducts"
      />

    </div>
  )
}


export default HomeView


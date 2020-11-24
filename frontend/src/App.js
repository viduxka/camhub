import React, { Component } from 'react';
import './App.css';
import Layout from './components/Layout/Layout';
import CamListContainer from './containers/CamListContainer/CamListContainer';

class App extends Component{
  render(){
    return (
      <Layout>
        <CamListContainer />
      </Layout> 
  );
  }
}

export default App;

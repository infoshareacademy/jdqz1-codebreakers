import React from 'react'

import {Navbar, FormGroup, FormControl, Button} from 'react-bootstrap'
import logo from "../../img/logo.svg"
import magnifier from "../../img/magnifier.png"
import ProductsList from "../SearchResultsList";
import searchResults from '../_utils/searchingProducts'
import {ListGroupItem, ListGroup} from "react-bootstrap";


const HomeView = () => (
    <div>
        <Navbar>
            <div className="navbar--contaiter">
                <Navbar.Header>
                    <Navbar.Brand>
                        <a className="logo"><img src={logo} alt="logo" height="50"/></a>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Navbar.Form pullLeft>
                        <FormGroup>
                            <FormControl type="text" placeholder="Znajdź produkt"/>
                        </FormGroup>
                        {' '}
                        <Button className="search-button" type="submit"><img src={magnifier} alt="search" height="35"/></Button>
                    </Navbar.Form>
                </Navbar.Collapse>
            </div>
        </Navbar>
        <div className="container products--container">
            <ListGroup>{
                searchResults.map(function (product) {
                    return <ListGroupItem >

                        <div className="product--info">
                            <div className="product--name">
                                <h2>{product.brand + " " + product.model}</h2>
                            </div>
                            <img className="product--img" src={product.image}/>
                        </div>
                        <div className="product--description">
                            {product.description}
                            <div className="product--details">
                                <h4>Kolor: {product.color}</h4>
                                <h4>Rozmiar: {product.size}</h4>
                            </div>
                        </div>
                        <div class="product--price">
                            <h3>{(product.price).toFixed(2)} zł</h3>
                            <Button bsStyle="success">Kup</Button>
                        </div>
                    </ListGroupItem >
                })
            }
            </ListGroup>
        </div>
    </div>
)


export default HomeView


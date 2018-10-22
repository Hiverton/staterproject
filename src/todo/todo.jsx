import React, { Component } from 'react'
import axios from 'axios'

import PageHeader from '../template/pageHeader';
import TodoForm from './todoForm';
import TodoList from './todoList';

const URL = 'http://localhost:3003/api/todos'

export default class Todo extends Component {

    constructor(props){
        super(props)
        this.state = {description: '', list:[]}
        this.handleChange = this.handleChange.bind(this)
        this.handleAdd = this.handleAdd.bind(this)
        this.handleRemove = this.handleRemove.bind(this)
        this.handleMarkAsDone = this.handleMarkAsDone.bind(this)
        this.handleMarkAsUnDone = this.handleMarkAsUnDone.bind(this)
        this.handleSearch = this.handleSearch.bind(this)
        this.handleClear = this.handleClear.bind(this)
        this.refresh()
    }

    refresh(description = ''){
        const search = description ? `&description__regex=${description}` : ''
        axios.get(`${URL}?sort=-createdAt${search}`)
            .then(res => this.setState({...this.props, description , list: res.data}))
    }

    handleMarkAsUnDone(todo){
        this.handleMarkAsDoneOrUnDone(todo, false)
    }

    handleMarkAsDone(todo){
        this.handleMarkAsDoneOrUnDone(todo, true)
    }

    handleMarkAsDoneOrUnDone(todo, done){
        axios.put(`${URL}/${todo._id}`, {...todo, done:done})
        .then(res => this.refresh(this.state.description))
        .catch(err => console.log(err))
    }

    handleSearch(){
        this.refresh(this.state.description)
    }

    handleRemove(todo){
        axios.delete(`${URL}/${todo._id}`)
            .then(res => this.refresh())
            .catch(err => console.log(err))
    }

    handleChange(e){
        this.setState({...this.state, description: e.target.value})
    }

    handleAdd(){
        const description = this.state.description
        console.log(`iniciou insert ${description}`)
        axios.post(URL, {description})
            .then(res => this.refresh())
            .catch(err => console.log(err))
    }

    handleClear(){
        this.refresh()
    }

    render() {
        return (
            <div>
                <PageHeader name='Tarefas' small='cadastro' />
                <TodoForm description={this.state.description} 
                            handleChange={this.handleChange}
                            handleAdd={this.handleAdd}
                            handleSearch={this.handleSearch}
                            handleClear={this.handleClear} />

                <TodoList list={this.state.list} 
                          handleRemove={this.handleRemove}
                          handleMarkAsDone={this.handleMarkAsDone}
                          handleMarkAsUnDone={this.handleMarkAsUnDone} />
            </div>
        )
    }
}
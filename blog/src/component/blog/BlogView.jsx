import React, { Component } from 'react'

// dil için
import { withTranslation } from 'react-i18next'

//resim
import cardPicture from "../../image/mountain.jpg"
import BlogApi from '../../services/BlogApi';

class BlogView extends Component {
    // Componentte görünür adı      
    static displayName = "Blog_View"

    constructor(props) {
        super(props);
        // STATE
        this.state = {
            id: this.props.match.params.id,
            // 1.YOL
            // header: null,
            // content: null,
            // image: null,
            // systemDate: null

            //2.YOL
            registerDto: {}
        }

        //BIND
        this.list = this.list.bind(this);
    }

    //CDM
    componentDidMount() {
        BlogApi.blogFindByIdApi(this.state.id).then((response) => {
            //console.log(response.data);
            //console.log(response.data.content);
            this.setState({
                //1.YOL
                // header: response.data.header,
                // content: response.data.content,
                // image: response.data.image,
                // systemDate: response.data.systemDate,
                //2.YOL
                registerDto: response.data
            })
        }).catch((error) => {
            console.log(error);
            console.log(error.name);
            console.log(error.message);
        })
    }

    //FUNCTION
    list() {
        this.props.history.push("/blog/list");
    }

    //RENDER
    render() {
        const { id, header, content, image, createdDate } = this.state.registerDto;
        //RETURN
        return (
            <React.Fragment>
                <div className="card " style={{ marginTop: "5rem" }} >
                    <img className="img-fluid w-50 mx-auto" src={cardPicture} alt="Title" />
                    <div className="card-body mx-auto">
                        <h4 className="card-title">View</h4>
                        <p className="card-text"> <b>ID:</b> {this.state.id}</p>
                        <p className="card-text"><b>header:</b>  {this.state.registerDto.header}</p>
                        <p className="card-text"><b>content:</b> {content}</p>
                        <p className="card-text"><b>image:</b> {image}</p>
                        <p className="text-danger"><b>DATE:</b> {createdDate}</p>
                        <button className="btn btn-outline-primary" onClick={this.list}>Liste</button>
                    </div>
                </div>
            </React.Fragment>
        ) //end return
    }//end  render
}//end  class

export default withTranslation()(BlogView)
import React, { Component } from 'react';

// i18n için
import { withTranslation } from "react-i18next";

// Service
import BlogApi from '../../services/BlogApi';

// ResuabilityBlogInput
import ResuabilityBlogInput from './ResuabilityBlogInput';

class BlogUpdate extends Component {
    // Componentte görünür adı      
    static displayName = "Blog_Update"

    constructor(props) {
        super(props);
        //STATE
        this.state = {
            blogDto: {},
            header: null,
            content: null,
            image: null,
            isRead: false,// Okumadan gönder butonu aktifleşmesin.
            spinnerData: false, // veri gönderirken loading olsun ve aynı anda birden fazla kayıt olunması
            validationErrors: {}, // backentten gelen hataları yakalamak 
            id: this.props.match.params.id, // update id parametresini almak
        }
        //BIND
        this.onChangeInputValue = this.onChangeInputValue.bind(this);
        this.blogUpdateSubmit = this.blogUpdateSubmit.bind(this);
        this.onChangeIsRead = this.onChangeIsRead.bind(this);
        this.cleanerData = this.cleanerData.bind(this);
    }

    //CDM
    componentDidMount() {
        BlogApi.blogFindByIdApi(this.state.id).then(
            (response) => {
                const findBlogDto = response.data;
                //console.log(findBlogDto);
                this.setState({
                    header: findBlogDto.header,
                    content: findBlogDto.content,
                    image: findBlogDto.image,
                })
            }
        ).catch((error) => {
            console.log("Blog isn't find")
        });
    }

    // Input 
    onChangeInputValue = (event) => {
        // 1.YOL
        // const name = event.target.name;
        // const value = event.target.value;
        // 2.YOL
        const { name, value } = event.target;
        console.log(name + " " + value);

        // Backendten gelen hataları yakalamak,
        // const backendError={}
        // ... anlamı kopyalama yapar.
        const backendError = { ...this.state.validationErrors }
        backendError[name] = undefined;

        // STATE
        this.setState({
            [name]: value, //state header,content,image
            backendError, // state Backendten gelen hataları yakalamak,
        })
    }

    // Okumadan Submit Butonu aktif olmasın
    onChangeIsRead = (event) => {
        this.setState({
            isRead: event.target.checked,
        })
    }

    // cleaner
    cleanerData(id) {
        // PHP
        this.props.history.push(`/list/update/${id}`)
    }

    // SUBMIT 
    blogUpdateSubmit = async (event) => {
        //browser sen dur bir şey yapma
        event.preventDefault();

        const { header, content, image } = this.state;
        const blogDto = {
            header, content, image
        }
        console.log(blogDto);

        // Spinner Data: çalışsın
        this.setState({ spinnerData: true })
        //   updateApi(id, blogDto) {
        BlogApi.blogUpdateApi(this.state.id, blogDto).then((response) => {
            if (response.status === 200) {
                // Veri gönderene kadar spinner(loading) çalışmasın
            }
            //PHP
            this.props.history.push("/blog/list");
        }).catch((error) => {
            console.error(error);

            // backentten gelen hataları yakala
            if (error.response.data.validationErrors) {
                this.setState({ validationErrors: error.response.data.validationErrors })//end state
                console.log(error.response.data.validationErrors)
            } //end if
            this.setState({ spinnerData: false })
        });
    }



    // RENDER
    render() {
        // i18n
        const { t } = this.props;

        //this.state.validationErrors.header
        const { validationErrors, isRead, spinnerData,
            header, content, image, id } = this.state;

        //RETURN
        return (
            <React.Fragment>
                <h1 className="text-center display-3">{this.props.t('update')}</h1>
                <form action="" method="post">

                    {/* <div className="form-group mt-3">
            <label htmlFor="header">{t('header')}</label>
            <input
              type="text" id="header" name="header"
              className="form-control" required="true"
              placeholder={t('header')} autoFocus={true}
              onChange={this.onChangeInputValue}
            />
            <div 
            className={this.state.validationErrors.name&&'invalid-feedback44 text-danger'} >
              {this.state.validationErrors.header}
            </div>
          </div> */}
                    <ResuabilityBlogInput
                        type="text" focus={true}
                        name="header" id="header"
                        placeholder={t('header')}
                        label={t('header')}
                        onChangeInput={this.onChangeInputValue}
                        error={validationErrors.header}
                        value={header}
                    />

                    <ResuabilityBlogInput
                        type="text" focus={false}
                        name="content" id="content"
                        placeholder={t('content')}
                        label={t('content')}
                        onChangeInput={this.onChangeInputValue}
                        error={validationErrors.content}
                        value={content} />

                    <ResuabilityBlogInput
                        type="text" focus={false}
                        name="image" id="image"
                        placeholder={t('image')}
                        label={t('image')}
                        onChangeInput={this.onChangeInputValue}
                        error={validationErrors.image}
                        value={image} />

                    {/*READING*/}
                    <div className="form-group mt-3">
                        <label htmlFor="is_read" className='me-2'>{t('isRead')}</label>
                        <input
                            type="checkbox" className='form-check-label'
                            id="is_read" name="is_read"
                            onChange={this.onChangeIsRead} />
                    </div>

                    {/* CLEAR */}
                    <button className="btn btn-danger mt-4 me-4" onClick={() => this.cleanerData(id)} >Temizle</button>

                    {/* SUBMIT */}
                    <button
                        className="btn btn-primary mt-4"
                        onClick={this.blogUpdateSubmit}

                        // hem okuma yapılmadan kapansın 
                        // hemde çoklu isteklerde kapatılsın
                        disabled={(!isRead) || (spinnerData)}
                    >
                        {/* 1.YOL (ternary) Spinner data  */}
                        {/* {(this.state.spinnerData) ? <div className="spinner-border spinner-border-sm text-warning me-2" role="status"></div>:"" }   */}
                        {/* 2.YOL (&&) Spinner data  */}
                        {(spinnerData) && <div className="spinner-border spinner-border-sm text-warning me-2" role="status"></div>}
                        Gönder
                    </button>
                </form>
            </React.Fragment>
        ); //end return 
    } //end render
} //end BlogUpdate

//i18n sarmaladı
export default withTranslation()(BlogUpdate)

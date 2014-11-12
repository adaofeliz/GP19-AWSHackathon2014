/**
 * Created by sevketufukgun on 12/11/14.
 */
Ext.onReady(function () {

    var dataSources = Ext.define('dataSources', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'label', type: 'string'},
            {name: 'enabled',  type: 'boolean'},
            {name: 'id',  type: 'int'}
        ]
    });

    var dataSourceStore = Ext.create('Ext.data.Store', {
        model: 'dataSources',
        storeId     : 'datasourceStore',
        autoLoad: false,
        data : []
    });


    var options = Ext.define('optionss', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'int'},
            {name: 'label', type: 'string'},
            {name: 'enabled',  type: 'boolean'}
        ]
    });

    var optionsStore = Ext.create('Ext.data.Store', {
        model: options,
        storeId     : 'optionsStore',
        autoLoad : false,
        data:[]
    });

    var searchTerms = Ext.define('searchTerms', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'label', type: 'string'},
            {name: 'source',  type: 'string'},
            {name: 'stage',  type: 'int'}
        ]
    });

    var searchTermsStore = Ext.create('Ext.data.Store', {
        model: searchTerms,
        storeId     : 'searchTermsStore',
        autoLoad : false,
        data:[]
    });

    var deletedSearchTerms = Ext.define('deletedSearchTerms', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'label', type: 'string'},
            {name: 'source',  type: 'string'},
            {name: 'stage',  type: 'int'}
        ]
    });

    var deletedTermsStore = Ext.create('Ext.data.Store', {
        model: deletedSearchTerms,
        storeId     : 'deletedTermsStore',
        autoLoad : false,
        data:[]
    });



    Ext.create('Ext.form.Panel', {
        renderTo: Ext.getBody(),
        bodyPadding: 10,
        width: '100%',
        title: 'Options',
        id:'form',
        items: [{
            xtype: 'fieldcontainer',
            fieldLabel: 'Data Sources',
            defaultType: 'checkboxfield',
            items: []
        },
            {
                xtype: 'combobox',
                label: 'Algorithm',
                //store: dataSources,
                id:'datasourcescombo',
                queryMode: 'local',
                displayField: 'label',
                valueField: 'id'

            },
            {
                xtype: 'fieldcontainer',
                fieldLabel: '',
                items: [
                    {
                        xtype:'textfield',
                        label:''
                    }
                ]
            },
            {
                xtype: 'fieldcontainer',
                fieldLabel: '',
                id:'searchresults',
                items: [
                    {
                        xtype:'grid',
                        label:'',
                        id: 'searchtermsGrid',
                        columns:[
                            {
                                text     : 'Term',
                                flex     : 1,
                                sortable : false,
                                dataIndex: 'label'
                            },
                            {
                                menuDisabled: true,
                                sortable: false,
                                xtype: 'actioncolumn',
                                width: 50,
                                items: [{
                                    iconCls: 'sell-col',
                                    tooltip: 'Remove Term',
                                    handler: function(grid, rowIndex, colIndex) {
                                        var rec = grid.getStore().getAt(rowIndex);
                                    }
                                }]
                            }
                        ]

                    }
                ]
            },
            {
                xtype: 'fieldcontainer',
                fieldLabel: '',
                id:'removedlist',
                items: [
                    {
                        xtype:'grid',
                        label:'',
                        id: 'removedTermsGrid',
                        columns:[
                            {
                                text     : 'Term',
                                flex     : 1,
                                sortable : false,
                                dataIndex: 'label'
                            },
                            {
                                menuDisabled: true,
                                sortable: false,
                                xtype: 'actioncolumn',
                                width: 50,
                                items: [{
                                    iconCls: 'buy-col',
                                    tooltip: 'Add Back',
                                    handler: function(grid, rowIndex, colIndex) {
                                        var rec = grid.getStore().getAt(rowIndex);
                                    }
                                }]
                            }
                        ]

                    }
                ]
            }
        ]
    });

    Ext.Ajax.request({
        url: 'http://localhost:9090/web-status/mock',
        success: function(response) {
            var json = Ext.decode(response.responseText);
            dataSourceStore.loadData(json.sources);
            searchTermsStore.loadData(json.activeTerms);
            deletedTermsStore.loadData(json.inactiveTerms);

            Ext.getCmp('datasourcescombo').setStore(dataSourceStore);
            Ext.getCmp('searchtermsGrid').setStore(searchTermsStore);
            Ext.getCmp('removedTermsGrid').setStore(deletedTermsStore);
        }
    });


    /**
     *Buidl the embed by form values
     */
    function buildEmbedByForm(form){
        var values = form.getForm().getValues();
        // Required values for the embed
        var divProps = {
            'uid' : values.uid,
            'pid' : values.pid,
            'keywords': values.keywords?values.keywords:'',
            'content-area': values.contentarea?values.contentarea:'',
        }
        var divElement = document.createElement('div');
        for(var i in divProps){
            divElement.setAttribute('data-'+i,divProps[i]);
        }
        divElement.setAttribute('class' , 'clipkit-playlist');

        var scriptElement = document.createElement('script');
        scriptElement.setAttribute('src',values.embedjspath);
        console.log(divElement,scriptElement);
    }
});

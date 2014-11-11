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

    var alg = Ext.define('algorithm', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'label', type: 'string'},
            {name: 'enabled',  type: 'boolean'}
        ]
    });

    var searchTerms = Ext.define('searchTerms', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'label', type: 'string'},
            {name: 'source',  type: 'string'},
            {name: 'stage',  type: 'int'}
        ]
    });

    var deletedSearchTerms = Ext.define('deletedSearchTerms', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'label', type: 'string'},
            {name: 'source',  type: 'string'},
            {name: 'stage',  type: 'int'}
        ]
    });


    var dataSources = [
        {
            "label" : 'Twitter',
            'enabled' : true,
            'id' : 1
        },
        {
            "label" : 'Google',
            'enabled' : false,
            'id' : 2
        },
        {
            "label" : 'Yahoo',
            'enabled' : true,
            'id' : 3
        }
    ];
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
            items: dataSources.map(function(dataSource) {
                return {
                    boxLabel: dataSource.label,
                    name: dataSource.id,
                    inputValue: dataSource.id,
                    id: "checkbox"+dataSource.id,
                    checked: dataSource.enabled
                }
            })
        },
            {
                xtype: 'combobox',
                label: 'Algorithm',

            },
            {
                xtype: 'fieldcontainer',
                fieldLabel: '',
                items: [
                    {
                        xtype:'textfield',
                        label:'',
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
                    }
                ]
            }
        ]
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

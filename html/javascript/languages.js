var indices = [
    'slides',
    'tags',
    'themes',
    'title',
    'text',
    'picture',
    'browse',
    'selected',
    'not selected',
    'theme',
    'select theme',
    'cancel',
    'submit',
    'delete',
    'upload image',
    'add slide',
    'add tag',
    'add theme',
    'tag',
    'date time',
    'from',
    'to',
    'override other tags',
    'no',
    'yes',
    'theme name',
    'css',
    'theme description'
];

var nb_NO = [
    'Sider',
    'Tags',
    'Temaer',
    'Tittel',
    'Tekst',
    'Bilde',
    'Velg Fil',
    'Valgt',
    'Ikke Valgt',
    'Tema',
    'Velg Tema',
    'Avbryt',
    'Lagre',
    'Slett',
    'Velg et bilde',
    'Legg til Side',
    'Legg til Tag',
    'Legg til Tema',
    'Tag',
    'Dato / Tid',
    'Fra',
    'Til',
    'Overskriv andre tags',
    'Nei',
    'Ja',
    'Temanavn',
    'CSS',
    'Beskrivelse av Tema'
];

var en_US = [
    'Slides',
    'Tags',
    'Themes',
    'Title',
    'Text',
    'Picture',
    'Browse',
    'Selected',
    'Not Selected',
    'Theme',
    'Select Theme',
    'Cancel',
    'Submit',
    'Delete',
    'Please upload a picture',
    'Add Slide',
    'Add Tag',
    'Add Theme',
    'Tag',
    'Date / Time',
    'From',
    'To',
    'Override other Tags',
    'No',
    'Yes',
    'Theme Name',
    'CSS',
    'Theme Description'
];

var lang = [];

function print(key){
    document.write(getTranslation(key));
}

function getTranslation(key){
    if(key == 'org') return 'Salem Menighet';
    var i = indices.indexOf(key);
    var value = lang[i];
    if(value == "" || value == undefined) value = key;
    return value;
}
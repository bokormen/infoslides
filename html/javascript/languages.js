var indices = [
    'slides',
    'tags',
    'themes',
    'name',
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
    'add slide'
];

var nb_NO = [
    'Sider',
    'Tags',
    'Temaer',
    'Navn',
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
    'Legg til Side'
];

var en_US = [
    'Slides',
    'Tags',
    'Themes',
    'Navn',
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
    'Add Slide'
];

var lang = [];

function print(key){
    if(key == 'org') document.write('Salem Menighet');
    else document.write(getTranslation(key));
}

function getTranslation(key){
    var i = indices.indexOf(key);
    var value = lang[i];
    if(value == "" || value == undefined) value = key;
    return value;
}
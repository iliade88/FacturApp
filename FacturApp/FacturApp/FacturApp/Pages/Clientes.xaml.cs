using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FacturApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Clientes : ContentPage
    {
        public Clientes()
        {
            InitializeComponent();
            BindingContext = new ClientesViewModel();
        }

        void Handle_ItemTapped(object sender, ItemTappedEventArgs e)
            => ((ListView)sender).SelectedItem = null;

        async void Handle_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            if (e.SelectedItem == null)
                return;

            await DisplayAlert("Selected", e.SelectedItem.ToString(), "OK");

            //Deselect Item
            ((ListView)sender).SelectedItem = null;
        }
    }



    class ClientesViewModel : INotifyPropertyChanged
    {
        public ObservableCollection<Models.Cliente> Items { get; }
        public ObservableCollection<Grouping<string, Models.Cliente>> ItemsGrouped { get; }

        public ClientesViewModel()
        {
            Items = new ObservableCollection<Models.Cliente>(new[]
            {
                new Models.Cliente("Antonio Hojaldre", "45692832B", "Marcelandia, numero 2, piso 1, letra A, Alicante", 965495321, "antonio@infoempresa.es"),
                new Models.Cliente("Mario Picapiedra", "23145678A", "Ramon y cajal, numero 1, piso 2, letra C, Alicante", 657654321, "mario@viajesta.com"),
                new Models.Cliente("Noé Sefue", "67432465T", "Conporit, numero 2, piso 1, letra A, San Vicente", 645324671, "noe@marcados.es"),
                new Models.Cliente("Oscar Alted", "46964357K", "Fiosiona, numero 2, piso 1, letra A, Elche", 965653421, "oscar@commito.es"),
                new Models.Cliente("Teresa Milongas", "12345678U", "Amancio, numero 2, piso 1, letra A, Elda", 666543671, "teresa@filder.es"),
                new Models.Cliente("Zaira Morcillo", "54326542R", "Tirant lo blanc, numero 2, piso 1, letra A, Petrer", 965123671, "zaira@zairanovias.es")
            });

            var sorted = from item in Items
                         orderby item.nombre
                         group item by item.nombre[0].ToString() into itemGroup
                         select new Grouping<string, Models.Cliente>(itemGroup.Key, itemGroup);

            ItemsGrouped = new ObservableCollection<Grouping<string, Models.Cliente>>(sorted);

            RefreshDataCommand = new Command(
                async () => await RefreshData());
        }

        public ICommand RefreshDataCommand { get; }

        async Task RefreshData()
        {
            IsBusy = true;
            //Load Data Here
            await Task.Delay(2000);

            IsBusy = false;
        }

        bool busy;
        public bool IsBusy
        {
            get { return busy; }
            set
            {
                busy = value;
                OnPropertyChanged();
                ((Command)RefreshDataCommand).ChangeCanExecute();
            }
        }


        public event PropertyChangedEventHandler PropertyChanged;
        void OnPropertyChanged([CallerMemberName]string propertyName = "") =>
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));

        public class Item
        {
            public string Text { get; set; }
            public string Detail { get; set; }

            public override string ToString() => Text;
        }

        public class Grouping<K, T> : ObservableCollection<T>
        {
            public K Key { get; private set; }

            public Grouping(K key, IEnumerable<T> items)
            {
                Key = key;
                foreach (var item in items)
                    this.Items.Add(item);
            }
        }
    }
}

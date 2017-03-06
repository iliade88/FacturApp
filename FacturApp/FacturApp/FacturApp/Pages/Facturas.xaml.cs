using FacturApp.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FacturApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Facturas : ContentPage
    {
        public Facturas()
        {
            InitializeComponent();
            BindingContext = new FacturasViewModel();
        }

        void Handle_ItemTapped(object sender, ItemTappedEventArgs e)
            => ((ListView)sender).SelectedItem = null;

        async void Handle_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            if (e.SelectedItem == null)
                return;

            //await DisplayAlert("Selected", e.SelectedItem.ToString(), "OK");
            var factura = e.SelectedItem;
            var detalle = new DetalleFactura();

            detalle.BindingContext = factura;
            await Navigation.PushAsync(detalle);

            //await Navigation.PushAsync(new DetalleFactura());

            //Deselect Item
            ((ListView)sender).SelectedItem = null;
        }
    }



    class FacturasViewModel : INotifyPropertyChanged
    {
        public ObservableCollection<Models.Factura> Items { get; }
        public ObservableCollection<Grouping<int, Models.Factura>> ItemsGrouped { get; }

        public FacturasViewModel()
        {
            Items = new ObservableCollection<Models.Factura>(new[]
            {
                new Factura(
                    1,
                    new Cliente("Mario Navarro", "48932958V","Calle 123", 657483759, "mario@viajesta.com"),
                    new DateTime(2017,3,2)),
                new Factura(
                    2,
                    new Cliente("Alberto Sapina", "64332448V","Calle 243", 643365771, "sapinamora@viajesta.com"),
                    new DateTime(2017,3,17)),
                new Factura(
                    3,
                    new Cliente("Cristobal Jesus", "64386484X","Calle 666", 965678754, "cristobal@viajesta.com"),
                    new DateTime(2017,1,20)),
                new Factura(
                    4,
                    new Cliente("Pepe Manuel", "12342958V","Calle 433", 652456531, "pepemanuel@miempresa.com"),
                    new DateTime(2017,2,1)),
                new Factura(
                    5,
                    new Cliente("Yaiza Carstairs", "34567453Y","Calle 321", 675643567, "yaiza@viajesta.com"),
                    new DateTime(2017,7,7)),
                new Factura(
                    6,
                    new Cliente("Noemi Nosabes", "35676433N","Calle 543", 675356743, "noemi@viajesta.com"),
                    new DateTime(2017,9,3)),
                new Factura(
                    7,
                    new Cliente("Jose Mulacen", "87654367Z","Calle gfj", 687950483, "josemula@info.com"),
                    new DateTime(2017,7,7)),
                new Factura(
                    8,
                    new Cliente("Antonio Manuel", "65738576Y","Calle 321", 675643567, "anton@viajesta.com"),
                    new DateTime(2017,7,7)),
                new Factura(
                    9,
                    new Cliente("Jorge Sdifjrirs", "34567453Y","Calle 321", 675643567, "jorga@viajesta.com"),
                    new DateTime(2017,7,7)),
                new Factura(
                    10,
                    new Cliente("Javier dsfoijdf", "53425467Y","Calle 123", 678345327, "javie@viajesta.com"),
                    new DateTime(2017,7,7)),
            });

            var sorted = from item in Items
                         orderby item.fecha.Month
                         group item by item.fecha.Month into itemGroup
                         select new Grouping<int, Models.Factura>(itemGroup.Key, itemGroup);

            ItemsGrouped = new ObservableCollection<Grouping<int, Models.Factura>>(sorted);

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

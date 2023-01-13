import React, { useEffect, useState } from 'react';
import { makeStyles } from '@mui/styles';
import { styled } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import { Container ,Paper,Button} from '@mui/material';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: '20px',
        },
    },
}));

export default function Creditcard() {
    const intitalValues = {customerName:"",card_number:"",max_limit:""}
    const [formValues,setFormValues] = useState(intitalValues);
    const [formErrors,setFormErrors] = useState({});
    const [isSubmit, setIsSubmit] = useState(false);
    const negative = {color: "red"};
    const positive = {color: "black"};
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[cards,setCards]=useState([])
    const classes = useStyles();
    const currency = "£";

    const StyledTableCell = styled(TableCell)(({ theme }) => ({
      [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
      },
      [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
      },
    }));

    const StyledTableRow = styled(TableRow)(({ theme }) => ({
      '&:nth-of-type(even)': {
        backgroundColor: theme.palette.action.hover,
      },
      // hide last border
      '&:last-child td, &:last-child th': {
        border: 0,
      },
    }));

    const handleChange = (e) => {
      console.log(formValues);
      console.log(e.target);
      const { name , value } = e.target;
      setFormValues({ ...formValues, [name]: value });
    }

    useEffect( () => {
      console.log(formErrors);
      if(Object.keys(formErrors).length === 0 && isSubmit){
        console.log(formValues);
      }
    },[formErrors]);

    const validate = (values) => {
      
      const errors = {};
      const card_regex = /^[1-9][0-9]{12,18}$/i;
      const double_regex = /^[1-9][0-9]{0,7}$/i;
      if(!values.customerName) {
        errors.customerName = "Card Holder's name is required";
      }else if(!values.customerName.length > 255){
        errors.customerName = "Card Holder's name limit exceeded";
      }

      if(!values.card_number){
        errors.card_number = "Card Number is required";
      }else if(!card_regex.test(values.card_number)){
        errors.card_number = "Card Number should be between 13-19 digits";
      }

      if(!values.max_limit){
        errors.max_limit = "Maximum Limit is required";
      }else if(isNaN(values.max_limit)){
        errors.max_limit = "Not a valid number";
      }else if(!double_regex.test(values.max_limit)){
        errors.max_limit = "Limit should be between 13-19 digits";
      }
      return errors;
    };

    const handleClick=(e)=>{
        e.preventDefault();
        setFormErrors(validate(formValues));
        setIsSubmit(true);
        const card={
          customerName:formValues.customerName,
          cardNumber:formValues.card_number,
          maxLimit:formValues.max_limit
        }
        console.log(card)
        fetch("http://localhost:8080/creditcard/add", {
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(card)
        }).then(() => {
            console.log("New Card added")
        })
    }
    
    useEffect(() => {
        fetch("http://127.0.0.1:8080/creditcard/getAll")
        .then(res=>res.json())
        .then((result)=>{
            setCards(result);
        }
    )
    },[])
  return (
    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1>Add Card</h1>
    
            <form className={classes.root}>
            <TextField id="outlined-basic1" name="customerName" label="Name" variant="outlined" fullWidth 
            value={formValues.customerName} 
            onChange={handleChange}/>
            <p>{formErrors.customerName}</p>
            <TextField id="outlined-basic2" name="card_number" label="Card Number" variant="outlined" fullWidth 
            value={formValues.card_number} 
            onChange={handleChange}/>
            <p>{formErrors.card_number}</p>
            <TextField id="outlined-basic3" name="max_limit" label="Limit" variant="outlined" fullWidth 
            value={formValues.max_limit} 
            onChange={handleChange}/>
            <p>{formErrors.max_limit}</p>
            <Button variant="contained" color="secondary" onClick={handleClick}>
                Submit
            </Button>
            </form>
        </Paper>
        <h1>Existing Cards</h1>

      <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <StyledTableRow>
            <StyledTableCell>Id</StyledTableCell>
            <StyledTableCell>Name</StyledTableCell>
            <StyledTableCell>Card Number</StyledTableCell>
            <StyledTableCell>Balance</StyledTableCell>
            <StyledTableCell>Limit</StyledTableCell>
          </StyledTableRow>
        </TableHead>
        <TableBody>
          {cards.map((card) => (
            <StyledTableRow
              key={card.cardId}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <StyledTableCell component="th" scope="row">
                {card.cardId}
              </StyledTableCell>
              <StyledTableCell>{card.customerName}</StyledTableCell>
              <StyledTableCell>{card.cardNumber}</StyledTableCell>
              <StyledTableCell style={card.balance >= 0 ? positive : negative}>{currency + card.balance}</StyledTableCell>
              <StyledTableCell>{currency + card.maxLimit}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>

    </Container>
  );
}